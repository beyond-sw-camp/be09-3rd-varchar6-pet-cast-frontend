<template>
    <section class="container">
    <div>
        <b-img :src="gatherDetail?.gatherImg" alt="모임 이미지" class="gather-logo" width="100"></b-img>
    </div>
        <article class="titlename">
            <div>
                <h4 class="gathername">강아지를 사랑하는 모임</h4>
            </div>
            <div>
                <h3>모임 이름</h3>
            </div>
            

        </article>
        <article class = "detail-info">
            <b-card title="모임 정보" class="card">
                <b-form class="memberlist">
                    <div>
                        <b-row>
                            <b-col v-for="member in gatherDetail?.members" :key="member.id" col="3" class="memberimg">
                                <div class="member-item">
                                    <div class="member-role">{{ member.role }}</div>
                                    <b-avatar :src="member.img" :text="member.initial" size="4rem"></b-avatar>
                                    <div class="member-name">{{ member.name }}</div>
                                </div>
                            </b-col>
                        </b-row>
                    </div>
                </b-form>

                
                <b-form-group label="모임 내용" label-for="nameInput">
                    <b-form-textarea
                        id="nameInput"
                        :value="gatherDetail?.contents"
                        required
                        placeholder="모임 내용"
                        readonly
                        style="resize: none;"
                        class="contents"
                    ></b-form-textarea>
                </b-form-group>
                
                
                <b-form-group label="URL" label-for="urlInput">
                    <b-form-textarea
                    id="urlInput"
                    :value="gatherDetail?.url"
                    readonly
                    style="resize: none;"
                    class="url"
                    ></b-form-textarea>
                </b-form-group>
                


                <div class="dated">
                    <b-row>
                        <b-col md="6">
                        <b-form-group label="생성 일자" label-for="createdAtInput">
                            <h6>{{ gatherDetail?.createAt }}</h6>
                        </b-form-group>
                        </b-col>
                        <b-col md="6">
                        <b-form-group label="수정 일자" label-for="updatedAtInput">
                            <h6>{{ gatherDetail?.updateAt }}</h6>
                        </b-form-group>
                        </b-col>
                    </b-row>
                </div>

<!-- <b-button type="submit" variant="primary">수정</b-button> -->
            </b-card>
        </article>
    </section>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const currentRoute = useRoute();
    const router = useRouter();
    const gatherList = ref([]);
    const gatherDetail = ref(null);


    const fetchGatherDetail = async () => {
        try{
            console.log('fetchGatherDetail 실행');
            const response = await fetch('http://localhost:8081/gatherdetail');
            if(!response.ok){
                throw new Error('정보 불러오기 실패');
            }
            // 통신해서 온 정보 포장 까기
            gatherList.value = await response.json();
            // 파라미터로 넘어온 id 값을 통해 해당 정보들 저장
            gatherDetail.value = gatherList.value[parseInt(currentRoute.params.id)-1];
        } catch (error) {
            console.error("데이터 로딩중 에러 발생", error);
        }
    }

    /* 반응형 변수들 */
    const form = ref({
        name: '',
        email: ''
    }); 


    onMounted(() => {
        fetchGatherDetail();
    })
</script>

<style scoped>
    .container {
        overflow: auto;
        height: 100vh;
    }
    .titlename{
        display: flex;
        flex-direction: row-reverse;
        justify-content: center;
        align-items: center;
        gap: 20px;
    }
    .gathername {
        position: relative; 
        background-color: #FFFED3;
        
    }
    .detail-info {
        padding-bottom: 7rem;
    }
    .card {
        background-color: #DDF5FF;
    }
    .memberlist{
        display: flex;
        justify-content: space-around;
        align-items: center;
        gap: 20px;
    }
    .member-name {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .contents {
        height: 200px; /* 5 units */
    }

    .url {
        height: 50px; /* 1 unit */
    }
    .dated {
        display: flex;
        align-items: center;
        justify-content: right;
    }

    /* .dated b-form-textarea {
        height: 15px;
    } */

    /* 모든 textarea에 공통으로 적용할 스타일 */
    .contents, .url, .date b-form-textarea {
        width: 100%;
        resize: none;
        overflow-y: auto; /* 내용이 넘칠 경우 스크롤바 표시 */
    }
    
</style>