import CatPayLoad from "../PayLoad/CatPayLoad";
export default class Cats{
    url;

    constructor(){
        this.url = 'http://localhost:8080/webapptest/api/cats';
    }

    async getAll(){
            
       
        const response = await fetch(this.url,
            {method: 'GET',
			headers: {
				mode: 'no-cors',
                credentials: 'include',
                "Access-Control-Allow-*": true,
                "Access-Control-Allow-Origin":"*",
                "Access-Control-Allow-Methods": "HEAD, GET, POST, PUT, PATCH, DELETE",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Content-Type": "application/json"
			}}
        );
        console.log(response);
        let charactersToReturn = [];
        for (const character of await response.json()) {
            let temporalCat = new CatPayLoad(character.id, character.name);

            charactersToReturn.push(temporalCat);
        }
        return charactersToReturn;
    }
}