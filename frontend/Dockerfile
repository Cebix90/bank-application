## Używamy obrazu Node.js do budowania aplikacji
#FROM node:20 as build
#
## Ustawiamy katalog roboczy w kontenerze
#WORKDIR /app
#
## Kopiujemy pliki projektu do kontenera
#COPY package*.json ./
#COPY public ./public
#COPY src ./src
#COPY index.html ./index.html
#
## Instalujemy zależności i budujemy aplikację
#RUN npm install
#RUN npm run build
#
## Tworzymy drugi etap z Nginx do serwowania aplikacji
#FROM nginx:alpine
#
## Kopiujemy pliki zbudowane przez React do katalogu Nginx
#COPY --from=build /app/dist /usr/share/nginx/html
#
## Ekspozycja portu Nginx (np. 80)
#EXPOSE 80
#
## Domyślna komenda startowa
#CMD ["nginx", "-g", "daemon off;"]
# --------------------------------------------------
# 1. Etap: Build (Node + Vite)
# --------------------------------------------------
FROM node:20 AS build

WORKDIR /app

# Kopiujemy dependencies
COPY package*.json ./
RUN npm install

# Kopiujemy resztę projektu: src/, public/, vite.config.js itp.
COPY . .

# Budujemy produkcyjny build Vite (generuje folder dist/)
RUN npm run build


# --------------------------------------------------
# 2. Etap: Nginx - serwowanie gotowych plików
# --------------------------------------------------
#FROM nginx:alpine
#
## Kopiujemy to, co wygenerował Vite (dist/) do katalogu Nginxa
#COPY --from=build /app/dist /usr/share/nginx/html
#
## Nginx domyślnie słucha na porcie 80
#EXPOSE 80
#
#CMD ["nginx", "-g", "daemon off;"]

FROM nginx:alpine

# Kopiujemy skompilowane pliki React (z folderu dist) do html
COPY --from=build /app/dist /usr/share/nginx/html

# Kopiujemy plik konfiguracyjny do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]