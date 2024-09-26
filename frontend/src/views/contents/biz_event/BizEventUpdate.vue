<template>
    <div class="edit-event-container">
        <h1>이벤트 수정</h1>
        <form @submit.prevent="updateEvent">
            <div class="form-group">
                <label for="event-title">이벤트 제목</label>
                <input id="event-title" v-model="event.title" type="text" required>
            </div>
            <div class="form-group">
                <label for="event-content">이벤트 내용</label>
                <textarea id="event-content" v-model="event.description" required></textarea>
            </div>
            <div class="form-group">
                <label>진행상태</label>
                <div class="status-buttons">
                    <button 
                        v-for="status in ['준비', '진행중', '완료']" 
                        :key="status"
                        type="button"
                        :class="{ active: event.status === status }"
                        @click="event.status = status"
                    >
                        {{ status }}
                    </button>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">수정하기</button>
                <button type="button" class="cancel-btn" @click="cancelEdit">삭제하기</button>
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const event = ref({});

const fetchEvent = async () => {
    try {
        const response = await fetch(`http://localhost:8082/business-events/${route.params.id}`);
        console.log(`http://localhost:8082/business-events/${route.params.id}`)
        if (!response.ok) {
            throw new Error('이벤트 정보를 가져오는데 실패했습니다.');
        }
        event.value = await response.json();
    } catch (error) {
        console.error('이벤트 정보 로딩 중 오류 발생:', error);
    }
};

const updateEvent = async () => {
    try {
        const response = await fetch(`http://localhost:8081/business-events/${route.params.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(event.value),
        });
        if (!response.ok) {
            throw new Error('이벤트 수정에 실패했습니다.');
        }
        alert('이벤트가 성공적으로 수정되었습니다.');
        router.push({ name: 'BusinessEvents' });
    } catch (error) {
        console.error('이벤트 수정 중 오류 발생:', error);
        alert('이벤트 수정에 실패했습니다. 다시 시도해주세요.');
    }
};

const cancelEdit = () => {
    if (confirm('정말로 이 이벤트를 삭제하시겠습니까?')) {
        // 여기에 이벤트 삭제 로직 구현
        alert('이벤트가 삭제되었습니다.');
        router.push({ name: 'BusinessEvents' });
    }
};

onMounted(() => {
    fetchEvent();
});
</script>

<style scoped>
.edit-event-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    padding-bottom: 80px;
    background-color: #e6f3ff;
    border-radius: 5px;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

textarea {
    height: 100px;
}

.status-buttons {
    display: flex;
    gap: 10px;
}

.status-buttons button {
    padding: 5px 10px;
    border: 1px solid #ccc;
    background-color: #fff;
    cursor: pointer;
}

.status-buttons button.active {
    background-color: #0066cc;
    color: white;
}

.form-actions {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
    position: fixed; 
    bottom: 20px; 
    left: 50%; 
    transform: translateX(-50%); 
    width: calc(100% - 40px); 
    max-width: 560px; 
}

.submit-btn,
.cancel-btn {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.submit-btn {
    background-color: #0066cc;
    color: white;
}

.cancel-btn {
    background-color: #f44336;
    color: white;
}
</style>