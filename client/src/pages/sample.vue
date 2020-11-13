<template>
    <tr v-for="(item, index) in pageData.content" :key="item.id" @click="showInfo(item)" style="cursor: pointer">
        <td>
            <div class="td-cell">
                {{index | pagingDataNo(pageData)}}
            </div>
        </td>
        <!-- 결제내역 -->
        <td>
            <div class="td-cell">
                <span class="badge badge-info-light">{{item.payRequestType | remotePayRequestTypeName}} 요청</span>
            </div>
        </td>
        <td>
            <div class="td-cell">
                <dl class="list-data__wrapper inline-layout stretch">
                    <dt class="list-data__text">상품명</dt>
                    <dd class="list-data__text">{{item.goodInfo.goodName}}</dd>
                </dl>
                <dl class="list-data__wrapper inline-layout stretch">
                    <dt class="list-data__text">상품금액</dt>
                    <dd class="list-data__text">{{item.priceInfo.totalPrice | numFilter}}원</dd>
                </dl>
                <dl class="list-data__wrapper inline-layout stretch"  v-if="item.customerInfo && item.customerInfo.customNo">
                    <dt class="list-data__text">{{$store.getters.bizCateStr | bizCateCustomerNo}}</dt>
                    <dd class="list-data__text">{{item.customerInfo.customNo}}</dd>
                </dl>
                <dl class="list-data__wrapper inline-layout stretch"  v-if="item.customerInfo && item.customerInfo.name">
                    <dt class="list-data__text">{{$store.getters.bizCateStr | bizCateCustomerName}}</dt>
                    <dd class="list-data__text">{{item.customerInfo.name}}</dd>
                </dl>
                <dl class="list-data__wrapper inline-layout stretch"   v-if="item.customerInfo && item.customerInfo.mobile">
                    <dt class="list-data__text">{{$store.getters.bizCateStr | bizCateCustomer}} 휴대폰</dt>
                    <dd class="list-data__text">{{item.customerInfo.mobile | telFilter}}</dd>
                </dl>
                <dl class="list-data__wrapper inline-layout stretch"   v-if="item.sellerMemo">
                    <dt class="list-data__text">판매자메모</dt>
                    <dd class="list-data__text">{{item.sellerMemo}}</dd>
                </dl>

            </div>
        </td>
        <!-- 결제자 -->
        <td>
            <span class="badge" :class="paymentHostTypeClass(item.hostType)">{{item.hostType | paymentHostTypeName}}</span>
        </td>
        <!-- 상태 -->
        <td>
            <div class="td-cell">
                <dl class="list-data__wrapper inline-layout">
                    <dt class="list-data__text">
                        <span>요청상태</span>
                    </dt>
                    <dd class="list-data__text">
                                                    <span>
                                                        <!-- 요청상태 -->
                                                        <span v-if="item.stateType === 'REQUEST'" class="text-additional">요청</span>
                                                        <span v-else-if="item.stateType === 'COMPLETE'" class="text-primary">완료</span>
                                                        <span v-else-if="item.stateType === 'REQUEST_CANCEL'" class="text-danger">요청취소</span>
                                                        <span v-else-if="item.stateType === 'DOING_PAYMENT'" class="text-additional">진행중</span>
                                                        <span v-else-if="item.stateType === 'LIMIT'" class="text-danger">만료</span>
                                                        <span v-else-if="item.stateType === 'FAIL'" class="text-danger">실패</span>
                                                    </span>
                    </dd>
                </dl>
                <dl class="list-data__wrapper inline-layout">
                    <dt class="list-data__text">
                        <span>결제상태</span>
                    </dt>
                    <dd class="list-data__text">
                                                <span>
                                                    <!-- 결제상태값 -->
                                                    <span v-if="!item.paymentState" class="text-info">대기</span>
                                                    <span v-else-if="item.paymentState === 'COMPLETE'" class="text-primary">완료</span>
                                                    <span v-else class="text-danger">취소</span>
                                                </span>
                    </dd>
                </dl>
            </div>
        </td>
        <!-- 요청일시 -->
        <td>
            <div class="td-cell">
                <dl class="list-data__wrapper inline-layout stretch w200">
                    <dt class="list-data__text">요청</dt>
                    <dd class="list-data__text">{{item.requestDate | dateFilter('yyyy-MM-dd HH:mm:ss')}}</dd>
                </dl>
                <dl v-if="item.stateType === 'REQUEST_CANCEL'" class="list-data__wrapper inline-layout stretch w200">
                    <dt class="list-data__text text-danger">취소</dt>
                    <dd class="list-data__text text-danger">{{item.requestCancelDate | dateFilter('yyyy-MM-dd HH:mm:ss')}}</dd>
                </dl>
            </div>
        </td>
        <td class="list-controller">
            <list-item-manage v-if="item.stateType === 'REQUEST'"
                              :manage-types="[itemManageType.REQUEST_CANCEL]"
                              :data="item"
                              @manage="manageHandle">
            </list-item-manage>
        </td>
    </tr>
</template>

<script>
export default {
name: "sample"
}
</script>

<style scoped>

</style>