package irish.bla.orderservice.util;

import irish.bla.orderservice.dto.*;
import irish.bla.orderservice.entity.PurchaseOrder;

public class EntityDtoUtil {

    public static void setTransactionRequestDto(RequestContext request) {
        request.setTransactionRequestDto(TransactionRequestDto.builder()
                .userId(request.getPurchaseOrderRequestDto().getUserId())
                .amount(request.getProductDto().getPrice()).build());
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext context) {

        return PurchaseOrder.builder()
                .userId(context.getPurchaseOrderRequestDto().getUserId())
                .productId(context.getPurchaseOrderRequestDto().getProductId())
                .amount(context.getProductDto().getPrice())
                .status(orderStatusFrom(context))
                .build();
    }

    public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder) {
        return PurchaseOrderResponseDto.builder()
                .orderId(purchaseOrder.getId())
                .amount(purchaseOrder.getAmount())
                .userId(purchaseOrder.getUserId())
                .productId(purchaseOrder.getProductId())
                .status(purchaseOrder.getStatus())
                .build();
    }

    private static OrderStatus orderStatusFrom(RequestContext context) {
        OrderStatus orderStatus = OrderStatus.FAILED;
        TransactionStatus status = context.getTransactionResponseDto().getStatus();
        if (TransactionStatus.APPROVED.equals(status)) {
            orderStatus = OrderStatus.COMPLETED;
        }
        return orderStatus;
    }
}
