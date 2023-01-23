<script setup>
import { characterStore } from '../stores/testStore';
import { ref } from 'vue';
let nameCat = ref('');
let idCat = ref(0);
let testStore = characterStore();

const makePost = ()=>{
  let data = {"id":idCat.value, "name":nameCat.value}
  let response = testStore.postNewCat(data);
  console.log(response);
}

</script>
<template>
  <div class="about">
    <input type="number" class="name" placeholder="enter the cat id" v-model="idCat">
    <input type="text" class="name" placeholder="enter the cat name" v-model="nameCat">
    <button @click="makePost">send info</button>
  </div>
</template>

<style>

</style>
