package com.dicv.truck.repo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {

	@Query("SELECT n from Notification n  where n.dicvUser.userId=:userId "
			+ " and n.eventGpsTime >=:eventGpsTime ORDER BY n.eventGpsTime DESC ")
	public List<Notification> getNotification(@Param("userId") Integer userId,
			@Param("eventGpsTime") Calendar eventGpsTime);

	@Query("Select n from Notification n  where n.vehicle.vehicleId IN :vehicleIds and n.alertType.alertTypeId IN "
			+ " :alertTypeIds   and n.dicvUser.userId =:userId and n.receivedDateTime >=:fromDate and n.receivedDateTime <=:toDate ")
	public List<Notification> getNotificationList(@Param("userId") Integer userId,
			@Param("vehicleIds") List<Integer> vehicleIds, @Param("alertTypeIds") List<Integer> alertTypeIds,
			@Param("fromDate") Calendar fromDate, @Param("toDate") Calendar toDate);

	@Query("SELECT N FROM Notification N WHERE N.emailAlert in (:emailAlertId)")
	public List<Notification> getUnSentNotification(@Param("emailAlertId") List<Integer> emailAlertId,
			Pageable Pageable);

}
