# TCC BT Day 3+4+5

**Những HttpStatus nào nên đc dùng trong trường hợp sau:**
1. Lấy dữ liệu thành công, trả về cho client `200 OK`
2. Khi Sinh Viên vừa đc tạo thành công `200 OK`
3. Khi xóa Sinh viên thành công `200 OK`
4. Yêu cầu từ client không hợp lệ (có thể truyền sai dữ liệu, ...) `403`
5. Không có mã xác thực trong request
6. Sinh viên không có quyền truy cập thông tin cá nhân của sinh viên khác `401`
7. Không tìm thấy path như url `404 Not found`
8. Version cũ không còn đc sử dụng 
9. Có quá nhiều Sinh viên truy cập Máy chủ nhà trường, máy chủ muốn từ chối yêu cầu. `403`
10. Server trong quá trình xử lý gặp lỗi logic ngoài ý muốn.
11. Yêu cầu dịch vụ, nhưng server không có dịch vụ đó. `404 Not found`
12. Máy chủ gọi sang hệ thống khác, nhưng hệ thống đó trả về quá muộn

**I.da hoan thanh**
* tao entity Student, Class, Depastment
* Cac chuc nang RestStudent: CRUD Student, export file , import file excel 
* su dung JPA de truy van du lieu

**II.chua hoan thanh**


