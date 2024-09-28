<template>
  <h4>Q&A 목록</h4>
  <div class="section-list">
    <div class="container-more"></div>
    <RouterLink to="/customer-qna/1">
      <p class="more"> 더보기 > </p>
    </RouterLink>

    <div class="item-container">
      <div v-if="qnas && qnas.length">
        <QnAItem v-for="(qna, index) in qnas" :key="index" :qna="qna" />
      </div>
      <div v-else class="empty-section">
        {{ emptyText }}
      </div>
    </div>
  </div>
</template>

<script setup>
import QnAItem from './item/QnAItem.vue';
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';

const props = defineProps({
  qnas: Array,
  emptyText: String,
  },
);
</script>

<style scoped>
.section-list {
  padding: 1px;
  padding-inline: 20px;
  background-color: #BAEBFF;
  border-radius: 10px;
}
.item-container {
  background-color: white;
  border: 1px solid #ABABAB;
  border-radius: 10px;
  padding: 10px;
  margin-block: 10px;
}
.container-more {
  display: flex;
}
.more {
  display: flex;
  justify-content: end;
  margin-right: 10px;
}
.empty-section {
  text-align: center;
  padding: 20px;
  border: 1px dashed #91d5ff;
}
</style>
