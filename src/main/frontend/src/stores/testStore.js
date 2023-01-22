import {defineStore} from 'pinia';
import Repository from '../ApiCall/Repository';


export const characterStore = defineStore({
    id: 'characters',
    state: ()=>({
        Characters: [],
    }),
    actions:{
        async AsignCharacters(){
            const MyApiCall = new Repository('cats')
            const Response = MyApiCall.chooseApi();

            this.Characters = await Response.getAll();
            
        },
        getChracterbyId(characterId){
            return this.Characters.find(item => item.id == characterId)
        }
    }

})