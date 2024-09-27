<template>
  <div class="section-item">
    <p>{{ item }}</p>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  item: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.section-item {
  padding: 10px;
  border-bottom: 1px solid #e0e0e0;
}
</style>
