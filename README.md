# Photographer
The `photographer-app` is designed to promote your brand and conduct business.
The application includes the following modules:
one is a [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) Rest API called `photographer-api`, another is a [ReactJS](https://reactjs.org/) applications called `photographer-client-ui` and 
`photographer-admin-ui`. The application secured by [Keycloak](https://www.keycloak.org/).

## Project diagram

![project-diagram](https://github.com/cubLife/Photographer-API/blob/main/Photographer%20application%20diagram.png)

## UML Class diagram

![UML Class diagram.png](https://github.com/cubLife/Photographer-API/blob/main/UML%20Class%20diagram.png)

## Applications

- ### photographer-api
`Spring Boot` Web Java backend application that exposes a REST API to manage **content**. Its secured endpoints can just be accessed if an access token (JWT) issued by `Keycloak` is provided.

`photographer-api` stores its data in a [`Postgresql`](https://www.postgresql.org/) database.

- ### photographer-admin-ui

  `ReactJS` frontend application where `admin` can manage content. In order to access the application, `admin` must login using his/her username and password. These credential are handled by `Keycloak`. All the requests coming from [`photographer-admin-ui`](https://github.com/cubLife/Photographer-admin-ui)
to secured endpoints in `photographer-api` have an access token (JWT) that is generated when `admin` log in.

- ### photographer-client-ui

  `ReactJS` frontend application where `users` can see offers, contact 
information, portfolio of photographer. Also, users can make an order of photo session,
  add a feedback about the photographer, write a message directly to email.
  [`photographer-client-ui`](https://github.com/cubLife/Photographer-client-ui) have no accesses to secured endpoints of photographer-api

## Prerequisites

- installed Java JDK 11
- installed Gradle
- installed Node.js
- downloaded Keycloak
- git (optional, to clone from repository)
- some text editor for editing configuration files (optional)

## How to run application locally

1. Setup and configure Keycloak server first. For more informatation [see a section below](#configure-keycloak-server-identity-and-access-management).
2. Clone photographer-api repository `https://github.com/cubLife/Photographer-API`.
3. Clone photographer-client-ui repository `https://github.com/cubLife/Photographer-client-ui`.
4. Clone photographer-client-ui repository `https://github.com/cubLife/Photographer-admin-ui`.
5. Run Keycloak in dev mode.
6. Run `photographer-api` with command `gradle bootrun`.
7. Run `photographer-admin-ui` with command `yarn start`.
8. Run `photographer-client-ui` with command `yarn start`.
9. Open [http://localhost:3000](http://localhost:3000) and [http://localhost:3001](http://localhost:3001) in a browser

### Configure Keycloak server (Identity and Access Management)

1. Download _Keycloak server_ from https://www.keycloak.org/downloads and run `bin/standalone.bat` or `bin/standalone.sh` script to start Keycloak application. Default Keycloak server port will be `8080`.

2. <p>
     <details>
     <summary>Setup Keycloak server at <i>localhost</i></summary>

   The steps to setup Keycloak specified below may be replaced with import of the Keycloak configuration file. To import the configuration file: go to Import on sidebar menu and upload previously exported configuration file.

3. Open the _Keycloak Admin Console_ (in our case at http://localhost:8080/auth/admin) and create admin user.

4. Add a new _realm_ for our isolated groups of applications and users (i.e. `photographer`). _Note: Default `master` realm should not be used as it is dedicated to manage Keycloak._

5. Create a user (any name). Give the user some temparary password (_Credentials_ tab) to maintain security (temporary password will be updated by the user at first login). Sign In to the user account console to verify the user is configured correctly at http://localhost:8080/auth/realms/photographer/account (remember, `8080` is the port where our Keycloak is situated and `photographer` is the name of our realm).

6. At the moment in order to use our application, each user should have mapped role `ADMIN` to it's user account. Go to _Keycloak Admin Console -> Roles -> Realm Roles -> Add Role_ and add `ADMIN` role. After that go to _Users_ and map `ADMIN` role for each user to be used in chat-service application (_Role Mappings ->_ move `ADMIN` role from _Available Roles_ to _Assigned Roles_) to map it.

7. Create a clients for Keycloak. Go to _Keycloak Admin Console -> Clients_ and create a client (name it `photographer-api`, and `photographer-admin`). Set _Valid Redirect URIs_ property of our client to `http://localhost:8081/*` and `http://localhost:3000/*` (where browser will be redirected after seccessfull login or logout, i.e. where our application is located) and _Web Origins_ property to `+`.

## Start in container

- In a terminal and inside `photographer-api` root folder run
  ```
  docker-compose up -d
  ```

- Wait for all Docker containers to be up and running. To check it, run
  ```
  docker-compose ps
  ```
  
