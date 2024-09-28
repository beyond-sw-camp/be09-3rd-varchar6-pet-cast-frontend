<template>
    <b-form class="memberlist">
        <div>
            <b-row>
                <b-col v-for="(member, index) in members" :key="member.id" col="3" class="memberimg">
                    <div class="member-item">
                        <div class="member-role" 
                        @click="deleteMember(member, index)"
                        :class="{ 'dash': index !== 0}">
                            {{ index === 0 ? member.role : '-' }}
                        </div>
                        <b-avatar :src="member.img" :text="member.initial" size="4rem"></b-avatar>
                        <div class="member-name">{{ member.name }}</div>
                    </div>
                </b-col>
            </b-row>
        </div>
        <b-modal v-model="showModal" title="멤버 삭제 확인" hide-footer>
            <p>{{ selectedMember ? `${selectedMember.name}님을 추방시키시겠습니까?` : '' }}</p>
            <div class="d-flex justify-content-end">
                <b-button variant="secondary" class="mr-2" @click="showModal = false">취소</b-button>
                <b-button variant="danger" @click="confirmDelete">삭제</b-button>
            </div>
        </b-modal>
    </b-form>
</template>

<script setup>
    import { ref } from 'vue';
    
    const props = defineProps({
        members: {
            type: Array,
            required: true
        }
    });

    const selectedMember = ref(null);
    const selectedIndex = ref(null);
    const showModal = ref(false);

    const deleteMember = (member, index) => {
        if(index !== 0){
            selectedMember.value = member;
            selectedIndex.value = index;
            showModal.value = true;
        }
    }

    const confirmDelete = () => {
        // 여기에 실제 삭제 로직을 구현합니다.
        console.log(`${selectedMember.value.name} 삭제`);
        showModal.value = false;
        // 삭제 후 필요한 작업 수행 (예: 부모 컴포넌트에 삭제 이벤트 emit)
    };
</script>

<style scoped>
    .memberlist {
        display: flex;
        justify-content: space-around;
        align-items: center;
        gap: 20px;
    }
    .memberimg {
        margin: 30px;
    }
    .member-item {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .member-role {
        margin-bottom: 0.5rem;
        font-size: 1rem;
    }
    .member-role.dash {
        color: red;
        font-size: 1.5rem; 
        font-weight: bold; 
    }
    .member-name {
        margin-top: 0.5rem;
        text-align: center; 
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bold;
    }
</style>