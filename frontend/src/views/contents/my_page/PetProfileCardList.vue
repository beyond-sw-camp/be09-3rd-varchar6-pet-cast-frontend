<template>
  <div class="pet-profile-card-list">
    <h3>애완동물 프로필 목록</h3>
    <div class="card-container">
      <PetProfileCard v-for="pet in petProfiles" :key="pet.id" :pet="pet" />
    </div>
  </div>
</template>

<script setup>
import PetProfileCard from './PetProfileCard.vue';
import { defineProps } from 'vue';

const props = defineProps({
  petProfiles: {
    type: Array,
    required: true,
  },
});
</script>

<style scoped>
.pet-profile-card-list {
  padding: 20px;
  background-color: #f0f9ff;
  border-radius: 10px;
  margin-bottom: 20px;
}
.card-container {
  display: flex;
  gap: 10px;
}
</style>
