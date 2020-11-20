# TCC BT Day 3+4+5

**I.da hoan thanh**
* tao entity Student, Class, Depastment
* Cac chuc nang RestStudent: CRUD Student, export file , import file excel 
* su dung JPA de truy van du lieu

**II.chua hoan thanh**


**Những HttpStatus nào nên đc dùng trong trường hợp sau:**
1.Lấy dữ liệu thành công, trả về cho client
2.Khi Sinh Viên vừa đc tạo thành công 
3.Khi xóa Sinh viên thành công 
4. Yêu cầu từ client không hợp lệ (có thể truyền sai dữ liệu, ...)
5. Không có mã xác thực trong request
6. Sinh viên không có quyền truy cập thông tin cá nhân của sinh viên khác
7. Không tìm thấy path như url
8. Version cũ không còn đc sử dụng
9. Có quá nhiều Sinh viên truy cập Máy chủ nhà trường, máy chủ muốn từ chối yêu cầu.
10. Server trong quá trình xử lý gặp lỗi logic ngoài ý muốn.
11. Yêu cầu dịch vụ, nhưng server không có dịch vụ đó
12. Máy chủ gọi sang hệ thống khác, nhưng hệ thống đó trả về quá muộn
