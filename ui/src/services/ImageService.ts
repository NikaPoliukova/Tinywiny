import axios from "axios";
import {Image} from "../model/Image";

class ImageService {

    async updateImage(image: Image): Promise<Image> {
        const response =await axios.put<Image>('http://localhost:8080/api/v1/admin/products', image);
        return response.data;
    }
    async deleteImage(image: Image): Promise<void> {
        const response = await axios.delete('http://localhost:8080/api/v1/admin/products');
        return response.data;
    }
}
export default new ImageService();