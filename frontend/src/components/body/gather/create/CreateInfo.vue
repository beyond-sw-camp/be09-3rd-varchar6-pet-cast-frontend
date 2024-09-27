<template>
    <article class="gather-info">        
        <div class="plus-icon">
        <input
            type="file"
            accept="image/png, image/jpeg"
            @change="onFileSelected"
            ref="fileInput"
            style="display: none"
        />
        <img
            :src="selectedImage || defaultImage"
            @click="triggerFileInput"
            class="avatar-image"
            alt="모임 이미지"
        ></img>
    </div>

    
        <div class="gather-name">
            <h3 class="gather-name">모임 이름</h3>
            <b-form-input v-model="text" placeholder="모임 이름을 작성하세요."></b-form-input>
        </div>
    </article>
</template>

<script setup>
    import { ref } from 'vue';

    const defaultImage = '/src/assets/icon/gather/plusmember.png';
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

    defineProps({
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


</script>

<style scoped>
    .gather-info {
        display: flex;
        align-items: center;
        gap: 2rem;
        margin-bottom: 2rem;
    }
    .gather-name {
        flex-grow: 1;
        font-size: 1.2rem;
        color: #666;
        margin-bottom: 1rem;
    }
    .plus-icon {
        cursor: pointer;
    }
    .avatar-image {
        width: 4rem;
        height: 4rem;
        object-fit: cover;
        border-radius: 50%;
    }

</style>