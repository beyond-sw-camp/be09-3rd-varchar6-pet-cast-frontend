<template>
  <h3>애완동물 프로필 목록</h3>
  <div class="pet-profile-card-list">
    <div class="card-container">
      <PetProfileCard v-for="pet in props.petProfiles" :key="pet.id" :pet="pet" />
    </div>
  </div>
</template>

<script setup>
import PetProfileCard from './PetProfileCard.vue';
import { defineProps } from 'vue';

const props = defineProps({
  petProfiles: Array
});
</script>

<style scoped>
h3 {
  margin-top: 30px;
}
.pet-profile-card-list {
  padding: 20px;
  background-color: #f0f9ff;
  border-radius: 10px;
  margin-bottom: 30px;
}
.card-container {
  display: flex;
  gap: 10px;
}
</style>
