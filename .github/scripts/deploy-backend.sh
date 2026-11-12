
#!/usr/bin/env bash
set -Eeuo pipefail

# Cau hinh TechVina Backend
BASE="/home/ubuntu/projects/TechVina/backend"
LIVE="$BASE/target/backend-0.0.1-SNAPSHOT.jar"
SERVICE="techvina-backend.service"
BACKUP_DIR="$BASE/backups"

BUCKET="techvina-backend-artifacts-2026"
REGION="ap-southeast-1"
HEALTH_URL="http://127.0.0.1:8080/actuator/health"

# Nhan commit SHA tu GitHub Actions
COMMIT_SHA="${1:-}"

if [[ ! "$COMMIT_SHA" =~ ^[a-f0-9]{40}$ ]]; then
  echo "ERROR: Invalid commit SHA"
  exit 1
fi

# Script phai chay voi quyen root
if [[ "$EUID" -ne 0 ]]; then
  echo "ERROR: Root permission required"
  exit 1
fi

# Ngan hai lan trien khai chay dong thoi
exec 9>/run/lock/techvina-backend-deploy.lock
flock -n 9 || {
  echo "ERROR: Another deployment is running"
  exit 1
}

# Tao thu muc sao luu
install -d -o ubuntu -g ubuntu -m 700 "$BACKUP_DIR"

NEW_JAR=$(mktemp "$BASE/target/.deploy-XXXXXX.jar")
BACKUP=""
SWITCH_STARTED=false
DEPLOY_OK=false

# Kiem tra trang thai ung dung
wait_for_health() {
  for i in $(seq 1 45); do
    RESPONSE=$(curl -fsS --max-time 3 \
      "$HEALTH_URL" 2>/dev/null || true)

    if [[ "$RESPONSE" == '{"status":"UP"}' ]]; then
      return 0
    fi

    sleep 2
  done

  return 1
}

# Tu dong rollback neu trien khai that bai
on_exit() {
  STATUS=$?
  trap - EXIT

  rm -f "$NEW_JAR"

  if [[ "$SWITCH_STARTED" == true &&
        "$DEPLOY_OK" == false ]]; then

    echo "DEPLOY FAILED - Starting rollback"

    systemctl stop "$SERVICE" || true

    if install -o ubuntu -g ubuntu -m 600 \
      "$BACKUP" "$LIVE" &&
      systemctl start "$SERVICE"; then

      if wait_for_health; then
        echo "ROLLBACK SUCCESS"
      else
        echo "ERROR: Rollback health check failed"
      fi
    else
      echo "ERROR: Could not restore Backend"
    fi

    STATUS=1
  fi

  exit "$STATUS"
}

trap on_exit EXIT

echo "Deploying commit: $COMMIT_SHA"

# Buoc 1: Tai JAR moi tu Amazon S3
aws s3 cp \
  "s3://$BUCKET/releases/backend-$COMMIT_SHA.jar" \
  "$NEW_JAR" \
  --region "$REGION" \
  --only-show-errors

# Buoc 2: Kiem tra file JAR
test -s "$NEW_JAR"
unzip -tq "$NEW_JAR" >/dev/null

# Buoc 3: Kiem tra Backend hien tai
test -s "$LIVE"
systemctl is-active --quiet "$SERVICE"

# Buoc 4: Sao luu phien ban dang chay
BACKUP=$(mktemp \
  "$BACKUP_DIR/backend-before-$(date -u +%Y%m%dT%H%M%SZ)-XXXXXX.jar")

install -o ubuntu -g ubuntu -m 600 "$LIVE" "$BACKUP"

echo "Backup saved: $BACKUP"

# Buoc 5: Thay the JAR va khoi dong lai
SWITCH_STARTED=true

systemctl stop "$SERVICE"

install -o ubuntu -g ubuntu -m 600 \
  "$NEW_JAR" "$LIVE"

systemctl start "$SERVICE"

# Buoc 6: Kiem tra Health Check
echo "Checking Backend health..."

if wait_for_health; then
  DEPLOY_OK=true
  echo "DEPLOY SUCCESS"
  echo "Commit: $COMMIT_SHA"
  echo "Health: $RESPONSE"
else
  echo "ERROR: Backend health check failed"
  exit 1
fi


# Buoc 7: Don dep Backup cu sau khi Deploy thanh cong
echo "Cleaning up old Backend backups..."

# Lay danh sach Backup tu dong, moi nhat truoc
mapfile -t BACKUPS < <(
  find "$BACKUP_DIR" -maxdepth 1 -type f \
    -name 'backend-before-????????T??????Z-*.jar' \
    -printf '%f\n' | LC_ALL=C sort -r
)

# Chi giu lai 3 ban Backup tu dong moi nhat
if (( ${#BACKUPS[@]} > 3 )); then
  for OLD_BACKUP in "${BACKUPS[@]:3}"; do
    rm -f -- "$BACKUP_DIR/$OLD_BACKUP" ||
      echo "WARNING: Could not delete $OLD_BACKUP"
  done
fi

echo "Backup cleanup completed"