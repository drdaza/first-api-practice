<script setup>
import { characterStore } from '../stores/testStore';
import { onBeforeMount } from 'vue';
let testStore = characterStore();
testStore.AsignCharacters();
</script>

<template>
  <main>
    <h1 v-for="character of testStore.Characters">
      {{character.id}} - {{ character.name }}
    </h1>
  </main>
</template>
