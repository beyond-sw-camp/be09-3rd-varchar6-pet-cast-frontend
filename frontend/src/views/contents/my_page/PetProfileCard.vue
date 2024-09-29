<template>
  <div class="pet-profile-card">
    <img :src="pet.image" alt="애완동물 이미지" class="pet-image" />
    <div class="pet-details">
      <h4>{{ pet.name }}</h4>
      <p>{{ pet.type }} / {{ pet.age }}살</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  pet: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.pet-profile-card {
  border: 1px solid #91d5ff;
  padding: 10px;
  border-radius: 10px;
  background-color: #fff;
  display: flex;
  align-items: center;
}
.pet-image {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-right: 10px;
}
.pet-details {
  display: flex;
  flex-direction: column;
}
</style>
