import axios from "axios";
import {Image} from "../model/Image";

class ImageService {

    async updateImage(productId: number, image: File) {
        const param = {productId: productId, image: image};
        return axios.post('http://localhost:8080/api/v1admin/products/' +productId, {
            data: param
        });
    }

    async deleteImage(productId: number): Promise<void> {
        const response = await axios.delete('http://localhost:8080/api/v1/admin/products/image/' + productId);
        return response.data;
    }
}

export default new ImageService();