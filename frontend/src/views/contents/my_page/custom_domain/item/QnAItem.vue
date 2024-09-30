<template>
  <div class="section-item">
    <div class="answered-container">
      <div class="answered">
      {{ status }}
      </div>
    </div>
    <div class="title-container">
      <div class="title">
      {{ props.qna.title }}
      </div>
    </div>
    <div class="created-container">
      <div class="created">
      {{ createdAt }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';

const createdAt = ref('');
const status = ref('');
const props = defineProps({
  qna: Object
});

if (props.qna.created_at) {
  createdAt.value = props.qna.created_at.split('T')[0];
}
if (props.qna.answered != null) {
  status.value = (props.qna.answered) ? '답변완료' : '미완료';
}

</script>

<style scoped>
.section-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  font-size: 15px;
  /* padding-block: 15px; */
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  background-color: #E4F7FF;
  margin-bottom: 10px;
}
.answered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  border: 1px solid black;
  font-size: 10px;
  padding: 10px;
  width: 60px;
  height: 20px;
  background-color: white;
}
.title-container {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  border: 1px solid black;
  padding: 10px;
  width: 300px;
  height: 20px;
  background-color: white;
}
.created-container {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  border: 1px solid black;
  padding: 10px;
  width: 90px;
  height: 20px;
  background-color: white;
}
.created {
  font-size: 10px;
}
</style>
