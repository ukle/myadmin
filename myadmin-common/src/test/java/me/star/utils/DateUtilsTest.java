    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtil.localDateTimeFormatyMdHms(now));
        Date date = DateUtil.toDate(now);
        LocalDateTime localDateTime = DateUtil.toLocalDateTime(date);
        System.out.println(DateUtil.localDateTimeFormatyMdHms(localDateTime));
        LocalDateTime localDateTime1 = DateUtil.fromTimeStamp(date.getTime() / 1000);
        System.out.println(DateUtil.localDateTimeFormatyMdHms(localDateTime1));
    }
}
