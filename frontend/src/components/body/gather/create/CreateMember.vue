<template>
    <div class="members-container">
        <div v-for="(member, index) in members" :key="index" class="plus-icon">
            <input
                type="file"
                accept="image/png, image/jpeg"
                @change="(event) => onFileSelected(event, index)"
                :ref="el => { if (el) fileInputs[index] = el }"
                style="display: none"
            />
            <div class="avatar" @click="() => triggerFileInput(index)">
                <img :src="member.image" :alt="`Member ${index + 1} image`" />
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const defaultImage = '/src/assets/icon/gather/plusmember.png';

    const members = ref([
        { image: defaultImage },
        { image: defaultImage },
        { image: defaultImage },
        { image: defaultImage }
    ]);
    
    const fileInputs = ref([]);
    
    const triggerFileInput = (index) => {
        fileInputs.value[index].click();
    };
    
    const onFileSelected = (event, index) => {
        const file = event.target.files[0];
        if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            members.value[index].image = e.target.result;
        };
        reader.readAsDataURL(file);
        }
    };
</script>

<style scoped>
    .members-container {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
    }
    .plus-icon {
        cursor: pointer;
        margin: 10px;
    }
    .avatar img {
        width: 4rem;
        height: 4rem;
        object-fit: cover;
        border-radius: 50%;
    }
</style>