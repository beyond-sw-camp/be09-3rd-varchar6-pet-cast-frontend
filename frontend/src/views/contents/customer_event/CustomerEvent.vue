<template>
    <div class="customer-events-container">
        <div class="customer-events">
            <h1>고객의 이벤트 내역</h1>
            <p>개수: {{ totalEvents }}개</p>

            <div class="event-list">
                <div v-for="event in displayedEvents" :key="event.id" class="event-item">
                    <img :src="event.imageUrl" :alt="event.title">
                    <div class="event-info">
                        <div class="event-header">
                            <h3 class="event-title">{{ event.title }}</h3>
                            <button 
                                v-if="event.canReview"
                                @click="writeReview(event)" 
                                class="review-btn"
                            >
                                리뷰 작성
                            </button>
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

const fetchEvents = async () => {
    try {
        const response = await fetch('http://localhost:8888/customerEvents');
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

const writeReview = (event) => {
    if (event.canReview) {
        router.push({ 
            name: 'CreateReview', 
            params: { eventId: event.id }
        });
    }
};

onMounted(() => {
    fetchEvents();
});
</script>

<style scoped>
.customer-events-container {
    padding-bottom: 80px; /* Footer의 높이보다 약간 더 큰 값 */
}

.customer-events {
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
    align-items: center;
    margin-bottom: 5px;
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

.review-status {
    margin-top: 10px;
}

.can-review {
    color: #4CAF50;
    font-weight: bold;
}

.cannot-review {
    color: #F44336;
    font-weight: bold;
}

.load-more-btn {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 20px;
    margin-bottom: 20px;
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.load-more-btn:hover {
    background-color: #0055aa;
}

.review-btn {
    padding: 5px 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 0.9em;
    transition: background-color 0.3s;
}

.review-btn:hover {
    background-color: #45a049;
}

.review-btn.disabled {
    background-color: #cccccc;
    color: #666666;
    cursor: not-allowed;
}
</style>
