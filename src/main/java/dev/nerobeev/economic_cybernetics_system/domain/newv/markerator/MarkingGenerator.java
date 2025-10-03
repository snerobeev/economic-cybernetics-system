package dev.nerobeev.economic_cybernetics_system.domain.newv.markerator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MarkingGenerator {

  private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

  public String generate(MarkingType type) {
    var date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    var key = type.getPrefix() + date;
    var sequence = counters.computeIfAbsent(key, k -> new AtomicInteger(0)).incrementAndGet();
    return String.format("%s-%s-%03d", type.getPrefix(), date, sequence);
  }
}
