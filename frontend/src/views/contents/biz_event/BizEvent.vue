<template>
    <div class="business-events-container">
        <div class="business-events">
            <h1>업체가 진행한 이벤트</h1>
            <p>개수: {{ totalEvents }}개</p>

            <div class="event-list">
                <div v-for="event in displayedEvents" :key="event.id" class="event-item">
                    <img :src="event.imageUrl" :alt="event.title">
                    <div class="event-info">
                        <div class="event-header">
                            <h3 class="event-title">{{ event.title }}</h3>
                            <div v-if="isUpdated" class="edit-btn-container">
                                <button @click="editEvent(event)" class="edit-btn">수정하기</button>
                            </div>
                        </div>
                        <div class="event-meta">
                            <span class="event-status">상태: {{ event.status }}</span>
                            <span class="event-category">
                                {{ event.category.join(', ') }}
                            </span>
                        </div>
                        <p>{{ event.description }}</p>
                    </div>
                </div>
            </div>

            <button v-if="hasMoreEvents" @click="loadMoreEvents" class="load-more-btn">
                이벤트 더보기
            </button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const events = ref([]);
const totalEvents = ref(0);
const displayedCount = ref(10);
const isUpdated = ref(false);

const fetchEvents = async () => {
    try {
        const response = await fetch('http://localhost:8888/business-events');
        if (!response.ok) {
            throw new Error('네트워크 응답이 올바르지 않습니다.');
        }
        const data = await response.json();
        console.log(data);
        events.value = data.events;
        totalEvents.value = data.totalCount;
    } catch (error) {
        console.error("이벤트 데이터를 가져오는 중 오류 발생:", error);
    }
};

const displayedEvents = computed(() => {
    return events.value.slice(0, displayedCount.value);
});

const hasMoreEvents = computed(() => {
    return displayedCount.value < events.value.length;
});

const loadMoreEvents = () => {
    displayedCount.value += 10;
};

const editEvent = (event) => {
    if (isUpdated.value) {
        router.push({ name: 'BizEventUpdate', params: { id: event.id } });
    } else {
        alert('이벤트를 수정할 권한이 없습니다.');
    }
};

const checkUpdatePermission = () => {
    const rolesString = localStorage.getItem('Roles');
    if (rolesString) {
        const roles = rolesString.split(',');
        isUpdated.value = roles.includes('COMPANY');
    } else {
        isUpdated.value = false;
    }
};

onMounted(() => {
    fetchEvents();
    checkUpdatePermission();
});
</script>

<style scoped>
.business-events-container {
    padding-bottom: 80px; /* Footer의 높이보다 약간 더 큰 값 */
}

.business-events {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
}

.event-list {
    display: grid;
    gap: 20px;
}

.event-item {
    display: flex;
    background-color: #e6f3ff;
    padding: 10px;
    border-radius: 5px;
    overflow: hidden;
    position: relative;
}

.event-item img {
    width: 100px;
    height: 100px;
    object-fit: cover;
    margin-right: 10px;
}

.event-info {
    flex-grow: 1;
}

.event-header {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-bottom: 5px;
}

.event-title-status {
    display: flex;
    flex-direction: column;
}

.event-title {
    margin: 0;
    font-size: 1.2em;
}

.event-status {
    font-size: 0.8em;
    color: #666;
    margin-right: 10px;
}

.event-meta {
    display: flex;
    align-items: center;
    margin-bottom: 5px;
}

.event-category {
    font-size: 0.9em;
    color: #0066cc;
    background-color: #e6f3ff;
    padding: 2px 5px;
    border-radius: 3px;
}

.edit-btn-container {
    display: flex;
    justify-content: flex-end;
}

.edit-btn {
    padding: 5px 10px;
    background-color: #f0f0f0;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 0.9em;
}

.edit-btn:hover {
    background-color: #e0e0e0;
}

.load-more-btn {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 20px;
    margin-bottom: 20px; /* 버튼 아래 여백 추가 */
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.load-more-btn:hover {
    background-color: #0055aa;
}
</style>