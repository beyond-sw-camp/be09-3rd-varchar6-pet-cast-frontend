<template>
  <div class="section-list">
    <h4>{{ title }}</h4>
    <div v-if="items && items.length">
      <SectionItem v-for="(item, index) in items" :key="index" :item="item" />
    </div>
    <div v-else class="empty-section">
      {{ emptyText }}
    </div>
  </div>
</template>

<script setup>
import SectionItem from './SectionItem.vue';
import { defineProps } from 'vue';

const props = defineProps({
  title: {
    type: String,
    required: true,
  },
  items: {
    type: Array,
    default: () => [],
  },
  emptyText: {
    type: String,
    default: '',
  },
});
</script>

<style scoped>
.section-list {
  padding: 20px;
  background-color: #d9f7be;
  border-radius: 10px;
  margin-bottom: 20px;
}
.empty-section {
  text-align: center;
  padding: 20px;
  border: 1px dashed #91d5ff;
}
</style>
