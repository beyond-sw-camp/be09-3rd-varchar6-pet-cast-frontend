<template>
    <b-modal
        id="answer_modal"
        title="문의 답변"
        v-model="isModalVisible"
    >
        <div class="question-section">
            <p><b>해당 문의:</b> {{ questionTitle }}</p>
            <p>{{ questionContent }}</p>
        </div>

        <b-form-textarea
            id="answer_area"
            v-model="answerContent"
            placeholder="답변을 입력하세요."
            rows="3"
            max-rows="6"
        ></b-form-textarea>

        <template #modal-footer="{ cancel, confirm }">
            <b-button variant="secondary" @click="cancel">취소</b-button>
            <b-button variant="primary" @click="confirm">등록</b-button>
        </template>
    </b-modal>
</template>

<script setup>
import { ref } from 'vue';

const isModalVisible = ref(false);
const questionTitle = ref('문의 제목 예시');
const questionContent = ref('문의 내용 예시');
const answerContent = ref('');

const cancel = () => {
    isModalVisible.value = false;
}

const confirm = () => {
    console.log('답변 등록:', answerContent.value);
    isModalVisible.value = false;
}

// export { isModalVisible, questionTitle, questionContent };
</script>

<style scoped>
.question-section {
    background-color: #f9f9f9;
    padding: 15px;
    border-radius: 5px;
    margin-bottom: 15px;
}
</style>
