package com.tdd_demo.records;

import java.util.List;

public record Bundle(long bundleId, List<Long> productIds, double discount) {
}
