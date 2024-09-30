<template>
  <h4>참여한 이벤트 목록</h4>
  <div class="section-list">
    <div class="container-more"></div>
    <RouterLink to="/customer-events/1">
      <p class="more"> 더보기 > </p>
    </RouterLink>

    <div class="item-container">
      <div v-if="events && events.length">
        <EventItem v-for="(event, index) in events" :key="index" :event="event" />
      </div>
      <div v-else class="empty-section">
        {{ emptyText }}
      </div>
    </div>
  </div>
</template>

<script setup>
import EventItem from './item/EventItem.vue';
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';

const props = defineProps({
  events: Array,
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
  margin-bottom: 20px;
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
