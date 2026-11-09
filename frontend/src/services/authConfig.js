// Đọc thông tin AWS từ biến môi trường Vite.
const region = import.meta.env.VITE_AWS_REGION;

const userPoolId = import.meta.env.VITE_COGNITO_USER_POOL_ID;

const clientId = import.meta.env.VITE_COGNITO_CLIENT_ID;

// Cấu hình OIDC để React đăng nhập với Amazon Cognito.
export const cognitoAuthConfig = {
  // App Client ID của Cognito.
  client_id: clientId,

  // Địa chỉ User Pool phát hành JWT.
  authority: `https://cognito-idp.${region}.amazonaws.com/${userPoolId}`,

  // Địa chỉ quay về sau khi đăng nhập.
  redirect_uri: import.meta.env.VITE_COGNITO_REDIRECT_URI,

  // Địa chỉ quay về sau khi đăng xuất.
  post_logout_redirect_uri: import.meta.env.VITE_COGNITO_LOGOUT_URI,

  // Sử dụng Authorization Code Flow.
  response_type: "code",

  // Các phạm vi quyền yêu cầu từ Cognito.
  scope: "openid email phone",
};
