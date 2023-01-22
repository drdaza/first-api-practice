import Cats from "./CatsRepository/Cats";

export default class Repository{
    api;

    constructor(api){
        this.api = api;
    }

    chooseApi(){
        if(this.api == 'cats') return new Cats();
    }

}