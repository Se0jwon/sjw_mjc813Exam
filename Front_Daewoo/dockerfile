 # Use official Nginx image
 FROM nginx:alpine


 # Copy custom nginx.conf
 COPY nginx.conf /etc/nginx/nginx.conf


 # Copy static HTML files
 COPY ./dist /usr/share/nginx/html

 EXPOSE 80 443

 CMD ["nginx", "-g", "daemon off;"]