package com.netradio.ws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.netradio.entity.Stream;
import com.netradio.entity.StreamView;
import com.netradio.service.StreamService;
import com.netradio.service.UserService;
import com.netradio.util.ImageUtils;

@Path("/streams")
public class StreamResource {

    private CacheManager cacheManager;
    
    private Cache cache; 

    private StreamService streamService;

    private UserService userService;

    private String prefix;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setStreamService(StreamService streamService) {
        this.streamService = streamService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream[] getStreams() {

        List<Stream> list = streamService.getStreams();

        return list.toArray(new Stream[list.size()]);
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public StreamView[] streams(@QueryParam("gid") List<Long> gids) {
        if (gids == null || gids.isEmpty()) {
            throw new WebApplicationException(404);
        }

        List<StreamView> list = streamService.getFlowsByGenres(gids
                .toArray(new Long[gids.size()]));

        return list.toArray(new StreamView[list.size()]);
    }*/

    @GET
    @Path("/{id: [0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public StreamView stream(@PathParam("id") Long id) {
        StreamView view = streamService.getStreamInfo(id);
        if (view == null) {
            throw new WebApplicationException(404);
        }
        return view;
    }

    @GET
    @Path("/{id: [0-9]*}")
    @Produces("image/*")
    public byte[] getResizedImage(@PathParam("id") Long id,
            @QueryParam("w") int w, @QueryParam("h") int h,
            @HeaderParam("accept") MediaType mt) throws IOException {
        System.out.println(this.cache.getName());
        String name = "" + id + mt.getSubtype() + h + w;
        Cache cache = cacheManager.getCache("imagecache");
        Element e = cache.get(name);
        if (e != null) {
            return (byte[]) e.getObjectValue();
        } else {
            File file = new File(prefix + File.separator + id + ".dat");
            if (!file.exists()) {
                throw new WebApplicationException(404);
            }

            BufferedImage image = ImageIO.read(file);

            if (w > 0 && h <= 0) {
                double ww = image.getWidth() / w;
                h = (int) Math.round(image.getHeight() / ww);
            }

            if (w <= 0 && h > 0) {
                double hh = image.getHeight() / h;
                w = (int) Math.round(image.getWidth() / hh);
            }

            if (w <= 0 || h <= 0) {
                w = image.getWidth();
                h = image.getHeight();
            }

            image = ImageUtils.createResizedCopy(image, w, h, true);
            ByteArrayOutputStream baos = null;
            byte[] imageInByte = null;
            try {
                baos = new ByteArrayOutputStream();
                ImageIO.write(image, mt.getSubtype(), baos);
                baos.flush();
                imageInByte = baos.toByteArray();
                cache.put(new Element(name, imageInByte));
            } catch (Exception ee) {
                ee.printStackTrace();
            } finally {
                baos.close();
            }

            return imageInByte;
        }
    }

    @POST
    @Path("/{id: [0-9]*}")
    public void chngFavorite(@QueryParam("status") boolean status,
            @PathParam("id") Long sid, @QueryParam("uname") String userName) {
        if (status) {
            streamService.setFavorite(userService.getUser(userName).getId(),
                    sid);
        } else {
            streamService.delFavorite(userService.getUser(userName).getId(),
                    sid);
        }
    }
}
