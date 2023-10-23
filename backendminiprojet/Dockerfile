FROM node:alpine
#Create a app directory
WORKDIR usr/src/app
#install app dependencies
COPY package*.json .
#Run npm install
RUN npm ci
#Bundle app source
COPY . .
CMD [ "npm", "start" ]