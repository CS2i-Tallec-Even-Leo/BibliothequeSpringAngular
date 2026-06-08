# =========================
# BUILD ANGULAR
# =========================
FROM node:24 AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .

RUN npm run build


# =========================
# SERVE WITH NGINX
# =========================
FROM nginx:alpine

COPY --from=build /app/dist/ /usr/share/nginx/html

EXPOSE 80