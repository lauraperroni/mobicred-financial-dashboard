FROM node:21-alpine
 
WORKDIR /app
 
COPY package*.json ./
 
RUN npm install -g npm@10.8.1
 
RUN npm install
 
COPY . .
 
RUN npm run build
 
EXPOSE 5173
 
CMD [ "npm", "run", "dev" ]