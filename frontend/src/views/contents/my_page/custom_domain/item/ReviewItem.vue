<template>
  <div class="section-item">
    <div class="review-container">
      <div class="title-container">
        <div class="title">
          {{ props.review.title }}
        </div>
        <div class="score">
          ★ {{ props.review.score }}
        </div>
      </div>
      <div class="comment">{{ props.review.comment }}</div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';

const categories = ref([]);
const props = defineProps({
  review: Object
});
// if (props.review.category) {
//   categories.value = props.event.category;
// }
// categories.value = props.event.category;
</script>

<style scoped>
.section-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding-block: 15px;
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  margin-bottom: 10px;
}
.title-container {
  display: flex;
  align-items: center;
}
.title {
  font-weight: bold;
  font-size: 20px;
}
.score {
  margin-left: 20px;
}
.review-container {
  display: flex;
  flex-direction: column;
  margin-inline: 10px;
}
.comment {
  margin-top: 10px;
  font-weight: 100;
  font-size: 15px;
}
</style>
