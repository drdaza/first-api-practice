import CatPayLoad from "../PayLoad/CatPayLoad";
export default class Cats{
    url;

    constructor(){
        this.url = 'http://localhost:8080/webapptest/api/cats';
    }

    async getAll(){
        const response = await fetch(this.url,
            {method: 'GET'}
        );
        
        let charactersToReturn = [];
        for (const character of await response.json()) {
            let temporalCat = new CatPayLoad(character.id, character.name);

            charactersToReturn.push(temporalCat);
        }
        return charactersToReturn;
    }
    async post(data){

        const response = await fetch(this.url,{method:'POST',body: JSON.stringify(data)});

        return response.json();
    }
}