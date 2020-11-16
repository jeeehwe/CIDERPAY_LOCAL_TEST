<template>
    <div id="main">
        <div v-if="isCorrect" class="main">
            <img src="@/assets/logo.png" class="space">
            <div>
                상품명 : {{ paymentRequest.goodName }}<br/>
                판매자 : {{ paymentRequest.sellerName }}<br/>
                연락처 : {{ paymentRequest.mobile }}<br/>
                결제금액 : {{ paymentRequest.price }}
            </div><br/>
            <div class="space">카드결제</div><br/>
            <button v-if="isRequest" class="btn btn-success" @click="payRequest">결제하기</button>
            <button v-if="!isRequest" class="btn btn-dark">결제완료</button>{{' '}}
            <button v-if="!isRequest" class="btn btn-outline-dark" @click="goBack">되돌아가기</button>
        </div>

        <div v-else>잘못된 주소입니다.</div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "PayUrl",
    data() {
        return {
            paymentRequest: {},
            isCorrect: false,
            isRequest: false,
        }
    },
    created() {
        axios
            .get(`http://localhost:8080/p/${this.$route.params.payUrl}`)
            .then(({data}) => {
                if (data !== "") {
                    this.isCorrect = true;
                    this.paymentRequest = data;
                    this.isRequest = (data.paymentState === 'PROCESS');
                } else
                    this.isCorrect = false;
            })
            .catch(() => this.isCorrect = false)
    },
    methods: {
        payRequest() {
            axios
                .post(`http://localhost:8080/p/request`, this.paymentRequest)
                .then(() => {
                    this.isRequest = !this.isRequest
                })
        },
        goBack() {
            this.$router.push('/')
        }
    }
}
</script>

<style scoped>

#main {
    padding: 3%;
}

.main {
    width: 300px;
    height: 400px;
    text-align: center;
    padding: 1%;
    border: 1px solid dimgray;
    border-radius: 30px;
}

.main > div {
    border: 1px solid lightgray;
    border-radius: 5px;
    width: 100%;
}

.main > :nth-child(2) {
    height: 200px;
    text-align: left;
    padding-top: 50px;
    padding-bottom: 100px;
    padding-left: 10px;
}

.space {
    padding: 3%;
}

</style>