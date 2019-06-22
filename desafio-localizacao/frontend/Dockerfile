FROM nginx:1.16-alpine
COPY dist /usr/share/nginx/html/
COPY default.conf /etc/nginx/conf.d/
COPY nginx.conf /etc/nginx/
CMD exec nginx -g "daemon off;"