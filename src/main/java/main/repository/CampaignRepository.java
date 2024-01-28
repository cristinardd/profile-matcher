package main.repository;

import main.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {
    List<Campaign> findAllByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

}
