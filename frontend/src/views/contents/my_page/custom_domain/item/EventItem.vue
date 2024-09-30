<template>
  <div class="section-item">
    <img class="event-profile" src="../../../../../assets/image/event_profile.png" alt="이벤트 프로필"/>
    <div class="event-container">
      <div class="event-info">
        <div>{{ props.event.title }}</div>
        <div class="event-status">상태: {{ props.event.status }}</div>
        <div class="event-categories">{{ categories.join(',') }}</div>
      </div>
      <div class="event-introduction">
        <div>{{ props.event.content }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';

const categories = ref([]);
const props = defineProps({
  event: Object
});
if (props.event.category) {
  categories.value = props.event.category;
}
// categories.value = props.event.category;
</script>

<style scoped>
.section-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  padding-block: 15px;
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  margin-bottom: 10px;
}
.event-profile {
  width: 70px;
  height: 70px;
  padding-inline: 3px;
}
.event-container {
  display: flex;
  flex-direction: column;
  margin-inline: 10px;
}
.event-info {
  display: flex;
  justify-content: space-between;
}
.event-introduction {
  margin-top: 10px;
}
</style>
