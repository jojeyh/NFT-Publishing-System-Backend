# NFT-Publishing-Application-Backend

This is the backend application for an NFT publishing application

## Endpoints
- `POST /login` - receives username/password and returns JWT upon success or error on failure
- `POST /user` - receives new user info, generates user object and adds to database, returns success/failure
- `GET /user` - returns private user page for logged in user
- `GET /user/{user_id}` - get public profile for user, to be viewed by other users
- `GET /user/{user_id}/image` - return all images for given user
- `POST /user/{user_id}/image` - receive new image object and add to database, return success/failure
- `GET /user/{user_id}/image/{image_id}` - returns specific image object, checks for privacy attribute and if true checks valid authorization
- `DELETE /user/{user_id}` - remove user from database, but keep any images that have minted NFTs attached to them
- `POST /nft` - add nft object to database, nft is constructed in the frontend using metamask/typescript, returns success/failure flag
- `GET /nft/{nft_id}` - returns specific nft object
- `DELETE /nft/{nft_id}` - deletes nft object from database, note that this does not delete the NFT from the blockchain, it is permanently stored there and we are just deleting our app's reference to it
- `PATCH /nft/{nft_id}` - partially update nft object, used to update owner

Currently in development..
To run, 

`git pull
cd NFT-Publishing-System-Backend
./gradlew build
./gradlew run
`

Note: postgresql should be set up and running on localhost:5432
