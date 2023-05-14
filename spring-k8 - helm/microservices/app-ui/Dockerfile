FROM node:14.20 as build
WORKDIR /app-ui

RUN npm install -g @angular/cli

COPY ./package.json .
RUN npm install
COPY . .
RUN ng build

FROM nginx as runtime
COPY --from=build /app-ui/dist/app-ui /usr/share/nginx/html
