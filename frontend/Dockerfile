FROM node:20 AS build

WORKDIR /app

# Kopiujemy dependencies
COPY package*.json ./
RUN npm install

# Kopiujemy resztę projektu: src/, public/, vite.config.js itp.
COPY . .

# Budujemy produkcyjny build Vite (generuje folder dist/)
RUN npm run build
FROM nginx:alpine

# Kopiujemy skompilowane pliki React (z folderu dist) do html
COPY --from=build /app/dist /usr/share/nginx/html

# Kopiujemy plik konfiguracyjny do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]