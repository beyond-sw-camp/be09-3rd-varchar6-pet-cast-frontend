<template>
    <div class="edit-event-container">
        <h1>이벤트 수정</h1>
        <div class="event-content">
            <div class="event-image-container">
                <img v-if="event.imageUrl" :src="event.imageUrl" alt="이벤트 사진" @click="openImageUpload" class="event-image">
                <button v-else @click="openImageUpload" class="upload-image-btn">이미지 업로드</button>
                <input id="event-image" type="file" @change="handleImageUpload" accept="image/*" ref="fileInput" style="display: none;">
            </div>
            <div class="event-details">
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
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="submit-btn" @click.prevent="updateEvent">수정하기</button>
            <button type="button" class="cancel-btn" @click="cancelEdit">삭제하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const event = ref({});
const fileInput = ref(null);

const fetchEvent = async () => {
    try {
        // const response = await fetch(`http://localhost:8082/business-event/${route.params.id}`);
        const response = await fetch('http://localhost:8082/business-event');
        console.log('응답 상태:', response.status);
        console.log('응답 URL:', response.url);
        
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태: ${response.status}`);
        }
        
        const data = await response.json();
        console.log('받은 데이터:', data);
        
        event.value = data;
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

 const openImageUpload = () => {
     // 이미지 수정 불가능하도록 함
    fileInput.value.click();
};

const handleImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            event.value.imageUrl = e.target.result;
        };
        reader.readAsDataURL(file);
    }
};

onMounted(() => {
    fetchEvent();
});
</script>

<style scoped>
.edit-event-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    padding-bottom: 100px;
    border-radius: 5px;
}

.event-content {
    display: flex;
    gap: 40px;
    margin-bottom: 20px;
    background-color: #e6f3ff; /* 여기에 하늘색 배경을 추가합니다 */
    padding: 20px; /* 내용물과 배경 사이에 여백을 추가합니다 */
    border-radius: 5px; /* 모서리를 둥글게 만듭니다 */
}

.event-image-container {
    flex: 0 0 300px; /* 이미지 컨테이너 크기 증가 */
    height: 300px; /* 이미지 컨테이너 높이 증가 */
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f0f0f0;
    border-radius: 5px;
    overflow: hidden;
}

.event-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    cursor: default;
}

.upload-image-btn {
    padding: 10px;
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.event-details {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.form-group {
    margin-bottom: 20px; /* 간격 증가 */
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
    font-size: 16px; /* 글자 크기 증가 */
}

textarea {
    height: 150px; /* 텍스트 영역 높이 증가 */
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