<template>
    <article class="gather-info">
        <div class="gather-logo">
            <input
                type="file"
                accept="image/png, image/jpeg"
                @change="onFileSelected"
                ref="fileInput"
                style="display: none"
            />
            <img
                :src="selectedImage || gatherImg"
                @click="triggerFileInput"
                class="avatar-image"
                alt="모임 이미지"
            ></img>
        </div>
        <div class="gather-name">
            <h3 class="gather-name">모임 이름</h3>
            <b-form-textarea
            id="contentsInput"
            v-model="name"
            placeholder="모임 명을 작성하세요."
            rows="3"
            max-rows="6"
            class="contents"
        ></b-form-textarea>
        </div>
    </article>
</template>

<script setup>
    import { ref, watch } from 'vue';

    const { gatherImg, gatherName } = defineProps({
        gatherImg: 
        {
            type: String,
            default: ''
        },
        gatherName: 
        {
            type: String,
            required: true
        }
    });

    const name = ref(gatherName);

    watch(
        () => gatherName,
        (newValue) => {
            name.value = newValue;
        }
    );

    const selectedImage = ref('');
    const fileInput = ref(null);

    const triggerFileInput = () => {
        console.log('Triggering file input');
        if (fileInput.value) {
            fileInput.value.click();
        } else {
            console.error('File input not found');
        }
    };

    const onFileSelected = (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
            selectedImage.value = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    };
</script>

<style scoped>
    .gather-info {
        display: flex;
        align-items: center;
        gap: 2rem;
        margin-bottom: 2rem;
    }
    .gather-logo {
        flex-shrink: 0;
        cursor: pointer;
    }
    .gather-name {
        flex-grow: 1;
    }
    .gather-name h3 {
        font-size: 1.2rem;
        color: #666;
        margin-bottom: 1rem;
    }
</style>