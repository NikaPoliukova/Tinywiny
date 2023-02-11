import axios from "axios";
import {Image} from "../model/Image";

class ImageService {

    async updateUserUser(image: Image): Promise<void> {
        await axios.put('http://localhost:8080/api/v1/products/image', image);
    }
    async deleteImage(image2: Image): Promise<void> {
        await axios.delete('http://localhost:8080/api/v1/products/${productId}/image');
    }
}