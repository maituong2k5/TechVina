
#!/usr/bin/env bash
set -Eeuo pipefail

# Xac dinh thu muc project
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
DEPLOY_SCRIPT="$ROOT/.github/scripts/deploy-backend.sh"

# Tao moi truong thu nghiem tam
TEST_DIR="$(mktemp -d)"
trap 'rm -rf "$TEST_DIR"' EXIT

LIVE="$TEST_DIR/backend.jar"
BACKUP="$TEST_DIR/backend-backup.jar"
NEW_JAR="$TEST_DIR/backend-new.jar"
SERVICE="techvina-backend.service"

# Gia lap JAR cu va JAR moi bi loi
printf 'BACKEND_VERSION_OK\n' > "$BACKUP"
printf 'BACKEND_VERSION_FAILED\n' > "$LIVE"
printf 'TEMPORARY_JAR\n' > "$NEW_JAR"

SWITCH_STARTED=true
DEPLOY_OK=false

# Gia lap systemctl, khong dung den EC2
systemctl() {
  case "$1" in
    stop|start) return 0 ;;
    *) return 1 ;;
  esac
}

# Gia lap lenh install de khoi phuc file
install() {
  local source_file="${@: -2:1}"
  local target_file="${@: -1}"
  cp "$source_file" "$target_file"
}

# Gia lap Health Check sau Rollback
wait_for_health() {
  cmp -s "$LIVE" "$BACKUP"
}

# Lay ham Rollback tu script trien khai that
source <(sed -n '/^on_exit() {/,/^}/p' "$DEPLOY_SCRIPT")

# Gia lap trien khai that bai
set +e
OUTPUT="$( (set +e; false; on_exit) 2>&1 )"
EXIT_CODE=$?
set -e

# Xac minh ket qua Rollback
[[ "$EXIT_CODE" -eq 1 ]]
[[ "$OUTPUT" == *"ROLLBACK SUCCESS"* ]]
cmp -s "$LIVE" "$BACKUP"
[[ ! -e "$NEW_JAR" ]]

echo "ROLLBACK_TEST_OK"
echo "Exit code: $EXIT_CODE"
echo "Backup restored successfully"
echo "Temporary JAR removed"
