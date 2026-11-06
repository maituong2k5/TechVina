import Table from "react-bootstrap/Table";
import React from "react";

import "./TechnicalSpecifications.scss";

/**
 * Component hiển thị thông số kỹ thuật theo từng nhóm.
 *
 * @param {Object} props
 * @param {Array} props.danhSachThongSo - Danh sách nhóm thông số.
 */
const TechnicalSpecifications = ({ danhSachThongSo }) => {
  return (
    <section className="technical-specifications">
      <h3 className="technical-specifications__title">Thông số kỹ thuật</h3>

      <Table responsive className="technical-specifications__table">
        <tbody>
          {danhSachThongSo.map((nhom, index) => (
            // Fragment có key để React nhận diện từng nhóm.
            <React.Fragment key={`${nhom.tenNhom}-${index}`}>
              {/* Hàng tiêu đề nhóm */}
              <tr className="technical-specifications__group">
                <td colSpan={2}>{nhom.tenNhom}</td>
              </tr>

              {/* Các thông số thuộc nhóm */}
              {nhom.danhSachThongSo.map((thongSo, index) => (
                <tr key={`${nhom.tenNhom}-${thongSo.tenThongSo}-${index}`}>
                  <td className="technical-specifications__name">
                    {thongSo.tenThongSo}
                  </td>

                  <td className="technical-specifications__value">
                    {thongSo.giaTri}
                  </td>
                </tr>
              ))}
            </React.Fragment>
          ))}
        </tbody>
      </Table>
    </section>
  );
};

export default TechnicalSpecifications;
