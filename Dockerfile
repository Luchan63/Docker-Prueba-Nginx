#FROM nginx:latest
FROM nginx:stable-alpine3.20-perl
COPY index.html /usr/share/nginx/html/index.html
COPY form.html /usr/share/nginx/html/form.html
COPY main.js /usr/share/nginx/html/main.js
COPY style.css /usr/share/nginx/html/style.css

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]