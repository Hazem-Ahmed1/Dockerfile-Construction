FROM node:19-alpine

#variants <>

# baseImage means important thing

WORKDIR /app

# make directory 

COPY package.json .

# copy from source to destination

RUN npm install

COPY . .

# copy all files 

EXPOSE 4000

# running  command 

CMD [ "npm" ,"start" ]



# docker build -t name image build
# docker image ls
# docker run --name contaienr -d -p 4000:4000 imageName (Port Forwarding) // container build
# docker rm name or id -f
# docker ps