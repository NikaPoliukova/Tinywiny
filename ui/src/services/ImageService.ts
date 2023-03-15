import axios from "axios";
import {Buffer} from "buffer";

class ImageService{
    async downloadImage(): Promise<any> {
        const response = await axios.get<any>('https://localhost:8080/api/v1/file', {
            withCredentials: true,
            responseType: 'arraybuffer'
        });
        return Buffer.from(response.data, 'binary').toString('base64');
    }
    deleteImage = async (productId : number) => {
        const response = await axios.delete(`http://localhost:8080/api/v1/admin/image/` +productId, {
            withCredentials: true
        });
        return response.data;
    }
}
export default new ImageService();